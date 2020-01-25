import { Category } from './category';
import { User } from './user';

export interface Item {
    id: number;
    name: string;
    startingPrice: number;
    postingTime: Date;
    author: User;
    category: Category;
    numberOfBids: number;
    price: number;
    over: boolean;
}
