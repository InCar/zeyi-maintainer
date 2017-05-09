var co = require('co');
var mysql = require('mysql');
var pinyin = require('pinyin');
var data = require('./data');
var corection = require('./corection');

var conn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '123456',
    database: 'hyr-0413-bak',
});

function doUpdateP(data) {
    return new Promise((resolve, reject) => {
        conn.query('update hyr_region_code set phone_code = ? where code = ?', [data.citycode, data.adcode], err => {
            if (err) {
                console.log(err);
                reject(err);
            } else {
                console.log(data);
                resolve(data);
            }
        });
    });
}

function checkCode(data) {
    return new Promise((resolve, reject) => {
        conn.query('select * from hyr_region_code where code = ?', [data.adcode], (err, rows) => {
            if (err) {
                console.log(err);
                reject(err);
            } else {
                if (rows.length < 1) {
                    console.log('------------', JSON.stringify(data));
                } else if (rows[0].name !== data.name) {
                    console.log('~~~~~~~~~~~~', JSON.stringify(data));
                } else {
                    console.log(JSON.stringify(data));
                }
                resolve(data);
            }
        });
    });
}

function addRegionP(data, parent) {
    var region = {code: data.adcode, parent_code: parent ? parent.adcode : 0, name: data.name, phone_code:  typeof data.citycode === 'string' ? data.citycode : null};
    if (data.level === 'province') region.type = 1;
    if (data.level === 'city') region.type = 10;
    if (data.level === 'district') region.type = 30;
    region.pinyin_abbr = pinyin(data.name, {style: pinyin.STYLE_FIRST_LETTER}).join('');
    return new Promise((resolve, reject) => {
        conn.query('insert into hyr_region_code set ?', [region], err => {
            if (err) {
                console.log(err);
                reject(err);
            } else {
                console.log(JSON.stringify(region));
                resolve(region);
            }
        });
    });
}

function updatePhoneP(data) {
    return new Promise((resolve, reject) => {
        conn.query('update hyr_region_code set phone_code = ? where code = ?', [data.areaCode, data.cityId.substring(0, 6)], err => {
            if (err) {
                console.log(err);
                reject(err);
            } else {
                conn.query('update hyr_region_code set phone_code = ? where parent_code = ? and type = 30', [data.areaCode, data.cityId.substring(0, 6)], err => {
                    if (err) {
                        console.log(err);
                        reject(err);
                    } else {
                        console.log(JSON.stringify(data));
                        resolve(data);
                    }
                });
            }
        });


    })
}

/*
co(function*() {
    for (var i = 0; i < data.length; i ++) {
        // if (typeof data[i].citycode === 'string') {
        //     // yield doUpdateP(data[i]);
        //     // yield checkCode(data[i]);
        // }
        yield addRegionP(data[i], null);
        if (data[i].districts.length) {
            for (var j = 0; j < data[i].districts.length; j ++) {
                // yield doUpdateP(data[i].districts[j]);
                // yield checkCode(data[i].districts[j]);
                yield addRegionP(data[i].districts[j], data[i]);
                if (data[i].districts[j].districts.length) {
                    for (var k = 0; k < data[i].districts[j].districts.length; k ++) {
                        if (data[i].districts[j].districts[k].level === 'district') {
                            yield addRegionP(data[i].districts[j].districts[k], data[i].districts[j]);
                        }
                    }
                }
            }
        }
    }
    conn.end();
}).catch(ex => {
    console.log(ex);
});
*/

co(function*() {
    for (var i = 0; i < corection.length; i++) {
        yield updatePhoneP(corection[i]);
    }
    conn.end();
}).catch(ex => {
    console.log(ex);
});