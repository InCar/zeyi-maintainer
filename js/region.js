let co = require('co');
let mysql = require('mysql');
let pinyin = require('pinyin');
let request = require('request');
let Promise = require('promise');
let coMysql = require('co-mysql');
let sleep = require('co-sleep');

let req = Promise.denodeify(request);

let options = {
    host: 'localhost',
    user: 'root',
    password: '123456',
    database: 'hyr-0413-bak',
};

let pool = mysql.createPool(options),
    sql = coMysql(pool);

let provinces = [];
co(function*() {
    let res = yield req({
        url: 'http://ali-city.showapi.com/areaName?level=1&maxSize=20&page=1', // 第一页
        method: 'GET',
        headers: {
            Authorization: 'APPCODE 2fe39b9369434ef2b6879a27bd1c65cc'
        }
    });
    let json = JSON.parse(res.body);
    provinces = json.showapi_res_body.data;

    res = yield req({
        url: 'http://ali-city.showapi.com/areaName?level=1&maxSize=20&page=2', // 第二页
        method: 'GET',
        headers: {
            Authorization: 'APPCODE 2fe39b9369434ef2b6879a27bd1c65cc'
        }
    });
    json = JSON.parse(res.body);
    provinces = provinces.concat(json.showapi_res_body.data);

    // 插入省份数据
    let inserted = [];
    for (let i = 0; i < provinces.length; i++) {
        if (provinces[i].id === '900000000000') continue; // 钓鱼岛忽略
        if (inserted.find(e => e === provinces[i].id)) {
            console.log('重复：' + provinces[i].id + ', ' + provinces[i].areaName);
            continue;
        }
        inserted.push(provinces[i].id);

        let row = {type: 1, parent_code: 0};
        row.code = provinces[i].id.substring(0, 6);
        row.name = provinces[i].areaName;
        row.phone_code = provinces[i].areaCode;
        row.pinyin_abbr = pinyin(row.name, {style: pinyin.STYLE_FIRST_LETTER}).join('').toUpperCase();
        yield sql.query('insert into hyr_region_code set ?', [row]);
        console.log(JSON.stringify(row));
    }

    // 插入城市数据
    for (let j = 0; j < inserted.length; j++) {
        res = yield req({
            url: 'http://ali-city.showapi.com/areaDetail?parentId=' + inserted[j],
            method: 'GET',
            headers: {
                Authorization: 'APPCODE 2fe39b9369434ef2b6879a27bd1c65cc'
            }
        });
        json = JSON.parse(res.body);
        let cities = json.showapi_res_body.data;
        let insertedCities = [];
        for (let k = 0; k < cities.length; k++) {
            if (insertedCities.find(e => e === cities[k].id)) {
                console.log('重复：' + cities[k].id + ', ' + cities[k].areaName);
                continue;
            }

            insertedCities.push(cities[k].id);
            let row = {type: 10, parent_code: inserted[j].substring(0, 6)};
            row.code = cities[k].id.substring(0, 6);
            row.name = cities[k].areaName;
            row.phone_code = cities[k].areaCode;
            row.pinyin_abbr = pinyin(row.name, {style: pinyin.STYLE_FIRST_LETTER}).join('').toUpperCase();
            yield sql.query('insert into hyr_region_code set ?', [row]);
            console.log(JSON.stringify(row));

            // 插入区县数据
            let res2 = yield req({
                url: 'http://ali-city.showapi.com/areaDetail?parentId=' + cities[k].id,
                method: 'GET',
                headers: {
                    Authorization: 'APPCODE 2fe39b9369434ef2b6879a27bd1c65cc'
                }
            });
            let json2 = JSON.parse(res2.body);
            if (json2.showapi_res_body.flag === false) {
                console.log('未查到区县数据：' + JSON.stringify(cities[k]));
                continue;
            }
            let districts = json2.showapi_res_body.data;
            let insertedDistricts = [];
            for (let m = 0; m < districts.length; m++) {
                if (districts[m].level > 3) continue;
                if (insertedDistricts.find(e => e === districts[m].id)) {
                    console.log('重复：' + districts[m].id + ', ' + districts[m].areaName);
                    continue;
                }

                insertedDistricts.push(districts[m].id);
                let row2 = {type: 30, parent_code: cities[k].id.substring(0, 6)};
                row2.code = districts[m].id.substring(0, 6);
                row2.name = districts[m].areaName;
                row2.phone_code = districts[m].areaCode;
                row2.pinyin_abbr = pinyin(row2.name, {style: pinyin.STYLE_FIRST_LETTER}).join('').toUpperCase();
                yield sql.query('insert into hyr_region_code set ?', [row2]);
                console.log(JSON.stringify(row2));
            }
            console.log('sleep 5s');
            yield sleep(5000);
        }
    }

    pool.end();
    console.log('finished');
}).catch(ex => {
    console.log(ex);
});

