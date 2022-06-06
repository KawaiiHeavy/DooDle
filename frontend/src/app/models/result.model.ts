import { Test } from "./test.model";
import { User } from "./user.model";

export class Result{
    id: string;
    test: Test;
    participant: User;
    score: number;
}