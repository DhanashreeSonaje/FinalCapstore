export class User{
    userId:number;
    name:String;
    username:String;
    password:String;
    eMail:String;
    role:String;
    isActive:boolean;
    securityQuestion:String;
    securityAnswer:String;

    constructor(name: any,username: any,password: any,eMail: any,role: any,isActive: any,securityQuestion: any,securityAnswer: any)
    {
        this.userId=null;
        this.eMail=eMail;
        this.name=name;
        this.username=username;
        this.password=password;
        this.role=role;
        this.isActive=null;
        this.securityQuestion=securityQuestion;
        this.securityAnswer=securityAnswer;
    }
}