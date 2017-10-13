# !/usr/bin/env python
# -*- coding: utf-8 -*-

import requests

import csv


def csv_to_list(path):
    with open(path, 'rt') as f:
        reader = csv.reader(f)
        your_list = list(reader)
        print (your_list)
    return your_list


user_items = csv_to_list('C:\\Users\\Meni\\PycharmProjects\\totake-uploadsamples\\user_items.csv')
trips = []
users_items = {}

for item in user_items:
    if (item[0] == "user_id"):
        continue
    if (int(item[0]) in users_items):
        users_items[int(item[0])].append(int(item[1]))
    else:
        users_items[int(item[0])] = [int(item[1])]

server_url = 'http://localhost'
for key, value in users_items.items():
    path = server_url + '/getFireBaseUser?userId=python' + str(key) + '&displayName=python' + str(key)
    r = requests.get(path)
    # print (r.json()['userId'])
    server_user_id = int(r.json()['userId'])
    # print (server_user_id)
    # r = requests.get(server_url + '/getFireBaseUser?userId=python'+'1'+'&displayName=python'+'1')
    # print(r.text)

    path = server_url + '/addNewTrip?userId=' + str(server_user_id)
    r = requests.get(path)
    server_trip_id = int(r.json()['tripId'])
    print (server_trip_id)

    for items in value:
        path = server_url + '/assignItemToUser?userId=' + str(server_user_id) + '&tripId=' + str(
            server_trip_id) + '&itemId=' + str(items) + '&amount=1'
        print(path)
        r = requests.get(path)
        print (r.text)

print ("end of script")