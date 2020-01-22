import { Category } from './category';
import { User } from './user';

export interface Item {
    id: number;
    name: string;
    startingPrice: number;
    author: User;
    category: Category;
}
