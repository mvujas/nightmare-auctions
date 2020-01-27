import { User } from './user';
import { Item } from './item';

export interface Bid {
    id: number;
    price: number;
    author: User;
    postingTime: Date;
    item: Item;
}
