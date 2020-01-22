import { Role } from './role';
import { Item } from './item';

export interface User {
    id: number;
    username: string;
    email: string;
    roles: Role[];
    items: Item[];
}
