export class ComplaintDisplayDTO {
    id!: number;
    text!: string;
    user!: string;
    staffMember!: string;
    center!: string;


    public constructor(obj?: any) {
        if (obj) {
            this.id = obj.id;
            this.text = obj.text;
            this.user = obj.user;
            this.staffMember = obj.staffMember;
            this.center = obj.center;
        }
    }
}