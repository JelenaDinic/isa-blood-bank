export class Equipment {
    type: string = "";
    quantity: number = 0;

    public constructor(obj?: any) {
        if (obj) {
            this.type = obj.type;
            this.quantity = obj.quantity;
        }
    }
}