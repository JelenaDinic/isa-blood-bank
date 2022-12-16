export class LoginUser {
    email: string = "vanja@gmail.com";
    password: string = ""

    public constructor(obj?: any) {
        if (obj) {
            // this.email = obj.email;
            this.password = obj.password;
            
        }
    }
}