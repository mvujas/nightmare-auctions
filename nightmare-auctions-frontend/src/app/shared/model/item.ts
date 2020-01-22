import { Category } from './category';
import { UserDetails } from '../domain/UserDetails';

export interface Item {
    id: number;
    name: string;
    startingPrice: number;
    category: Category;
}
