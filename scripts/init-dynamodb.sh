#aws dynamodb --endpoint-url=http://localhost:4566 create-table \
#--table-name riscos-analiticos-interno \
#--attribute-definitions \
#AttributeName=id,AttributeType=S \
#AttributeName=data,AttributeType=S \
#--key-schema \
#AttributeName=id,KeyType=HASH \
#AttributeName=data,KeyType=RANGE \
#--provisioned-throughput \
#ReadCapacityUnits=10,WriteCapacityUnits=5