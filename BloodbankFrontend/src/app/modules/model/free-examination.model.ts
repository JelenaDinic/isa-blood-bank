export class FreeExamination {
    dateTime!: string;
    duration: number = 15;
    staffId! : number;

    public constructor(obj?: any) {
        if (obj) {
            this.dateTime = obj.dateTime;
            this.duration = obj.duration;
            this.staffId = obj.staffId;
        }
    }
}