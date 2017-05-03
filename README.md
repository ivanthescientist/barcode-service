**Barcode Lookup Service**

Provides an easy and straightforward RESTful API to check (and update) 
list of used barcodes stored in textual representation.

**REST API Description:** 

`POST /api/barcode/{barcode}` - adds barcode to collection. \
Response Body: blank.\
Response Status: OK(200) if successful, Conflict(409) if barcode already existed. 

Example: \
`POST /api/barcode/130993939BBB`

`GET /api/barcode/{barcode}` - checks if barcode is present in collection. \
Response Body: blank.\
Response Status: OK(200) if found, Not Found(404) if not. 

Example: \
`GET /api/barcode/130993939BBB`

`{barcode}` - is an alphanumeric string with length ranging from 13 to 25 symbols. 