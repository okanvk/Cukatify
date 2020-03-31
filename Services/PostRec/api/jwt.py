from base64 import b64decode
import jwt

def decode_jwt(encoded_jwt):
    decoded_jwt = jwt.decode(encoded_jwt, b64decode('thingsboardDefaultSigningKey'), algorithms=['HS256'])
    return decoded_jwt['sub']

