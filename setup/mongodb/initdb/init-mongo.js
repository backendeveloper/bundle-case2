use messages

db.createCollection('messages')

// db.createUser({
//     user: "bundle",
//     pwd: "bundle",
//     roles: [
//         {
//             role: "readWrite",
//             db: "messagedb"
//         }
//     ]
// });
//
// db = db.getSiblingDB('messagedb');
// db.createCollection('messages');