import { Category } from './category';
import { User } from './user';
import { Bid } from './bid';

export interface Item {
    id: number;
    name: string;
    details: string;
    startingPrice: number;
    postingTime: Date;
    author: User;
    category: Category;
    numberOfBids: number;
    price: number;
    over: boolean;
    bids: Bid[];
}
