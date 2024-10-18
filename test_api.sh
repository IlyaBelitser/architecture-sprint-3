#!/bin/bash

printf "\n*** ТЕСТИРОВАНИЕ API ***\n"
printf "\n--------------------\n"
printf "   -=ТЕЛЕМЕТРИЯ=-"
printf "\n--------------------\n"
printf "\nДобавить новые данные телеметрии для устройства\n\n"
curl -X 'POST' \
  'http://localhost:8000/api/devices/a0ee6d1c-994e-4299-8452-7ea919119f8c/telemetry' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "data": "{\"deviceId\": 123, \"temperature\": 25.5}",
  "receivedAt": "2024-10-16T20:30:56.004Z",
  "ipAndPort": "192.168.0.5:8080"
}'

printf "\n\nПолучить последних данных телеметрии устройства\n\n"
curl -X 'GET' \
  'http://localhost:8000/api/devices/a0ee6d1c-994e-4299-8452-7ea919119f8c/telemetry/latest' \
  -H 'accept: */*'

printf "\n\n"
printf "\n--------------------\n"
printf "   -=УСТРОЙСТВА=-"
printf "\n--------------------\n"
printf "\nИнформация об устройстве\n\n"
curl -X 'GET'  'http://localhost:8000/api/devices/a0ee6d1c-994e-4299-8452-7ea919119f8c'  -H 'accept: */*'

printf "\n\nОбновить статус устройства\n\n"
curl -X 'PUT' \
  'http://localhost:8000/api/devices/a0ee6d1c-994e-4299-8452-7ea919119f8c/status' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '"on"'

printf "\nДобавить новое устройство\n\n"
curl -X 'POST' \
  'http://localhost:8000/api/devices/' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "deviceTypeId": 1,
  "moduleId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "scenarioId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "name": "Kitchen",
  "serialNumber": "HD-1"
}'

printf "\nОтправить команду на устройство\n\n"
curl -X 'POST' \
  'http://localhost:8000/api/devices/a0ee6d1c-994e-4299-8452-7ea919119f8c/command' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d 'set_temperature 24.5'