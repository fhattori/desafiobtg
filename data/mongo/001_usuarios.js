db = db.getSiblingDB('admin');
db.createUser(
  {
    user: "adminbtg",
    pwd: "pass123456",
    roles: [ { role: "userAdminAnyDatabase", db: "admin" }, "readWriteAnyDatabase" ]
  }
);
