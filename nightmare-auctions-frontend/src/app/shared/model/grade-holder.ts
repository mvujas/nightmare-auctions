import { User } from './user';

export interface GradeHolder {
    id: number;
    value: number;
    givingGrade: User;
    receivingGrade: User;
    itemId: number;
}
