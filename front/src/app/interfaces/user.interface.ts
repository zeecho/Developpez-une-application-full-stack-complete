export interface User {
  id: number;
  email: string;
  username: string;
  admin: boolean;
  password: string;
  topics: number[];
  createdAt: Date;
  updatedAt?: Date;
}
