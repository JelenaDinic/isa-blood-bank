export class UserProfileDisplayDTO{
    public firstName: String = "";
    public lastName: String = "";
    public email: String = "";
    public street: String = "";
    public number: String = "";
    public city: String = "";
    public country: String = "";
    public phoneNumber: String = "";
    public personalId: String = "";
    public profession: String = "";
    public professionInfo: String = "";
    public dateOfBirth: String = "";
    public gender: String = "";

    public constructor(obj?: any) {
        if(obj){

        }
    }
}