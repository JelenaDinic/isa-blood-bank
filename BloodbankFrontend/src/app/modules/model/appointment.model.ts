export class Appointment {
    id!: number;
    dateTime!: Date;
    duration: number = 0;
    status: string = "";
    user: string = ""

    public constructor(obj?: any) {
        if (obj) {
            this.id = obj.id;
            this.dateTime = obj.dateTime;
            this.duration = obj.duration;
            this.status = obj.status;
            this.user = obj.user;
            
        }
    }
}