import { User } from "./user.model";

export class AppointmentDisplay {
    id!: number;
    dateTime!: Date;
    duration: number = 0;
    status: string = "";
    isCancelled: string = '';
    isScheduled: string = '';
    registeredUser!: User;
    ;

    public constructor(obj?: any) {
        if (obj) {
            this.id = obj.id;
            this.dateTime = obj.dateTime;
            this.duration = obj.duration;
            this.status = obj.status; 
            this.isCancelled = obj.isCancelled; 
            this.isScheduled = obj.isScheduled;  
            this.registeredUser = obj.registeredUser;       
            
        }
    }
}