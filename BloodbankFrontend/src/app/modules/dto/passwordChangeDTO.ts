export class PasswordChangeDTO {
    newPassword!: string;
    confirmNewPassord!: string;
    userId!: number;


    public constructor(obj?: any) {
        if (obj) {
            this.newPassword = obj.newPassword;
            this.confirmNewPassord = obj.confirmNewPassord;
            this.userId = obj.userId;
        }
    }
}