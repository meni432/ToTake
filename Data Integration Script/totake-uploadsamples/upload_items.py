import csv

import pymysql



def csv_to_list(path):
    with open(path, 'rt', encoding="utf8") as f:
        reader = csv.reader(f)
        your_list = list(reader)
        print(your_list)
    return your_list


items = csv_to_list('C:\\Users\\Meni\\PycharmProjects\\totake-uploadsamples\\items-utf8.csv')


connection  = pymysql.connect(user='tahel', password='tahelmeni',
                              host='totake.website',
                              database='app_service_4')

connection .set_charset('utf8')


# INSERT INTO `app_service_4`.`items` (`item_id`, `en_name`, `he_name`, `status`) VALUES ('item_id', 'en_name', 'he_name', 'status');
def addItemToDb(item_id, item_name):
    cursor = connection .cursor()
    add_item = ("INSERT INTO items "
                "(item_id, en_name, he_name, status) "
                "VALUES (%s, %s, %s, %s)")
    data_item = (int(item_id), item_name.encode('utf8'), item_name, 0)
    cursor.execute(add_item, data_item)
    print(cursor.description)
    cursor.close()
    connection.commit()


flag = True
for item in items:
    if (flag):
        flag = False
        continue
    print("add item : " + item[0] + " "  + item[2])
    addItemToDb(item[0], item[2])
