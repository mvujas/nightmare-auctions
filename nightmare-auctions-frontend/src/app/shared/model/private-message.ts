import { User } from './user';

export interface PrivateMessage {
    id: number;
    text: string;
    sendingTime: Date;
    sender: User;
    receiver: User;
}
