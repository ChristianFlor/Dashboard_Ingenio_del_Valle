class Engineer{
    constructor(name,lastname,id,username,password,email) {
        this.name = name;
        this.lastname = lastname;
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        Object.seal(this);
    }

    toJson(){
        return JSON.stringify(this);
    }
}