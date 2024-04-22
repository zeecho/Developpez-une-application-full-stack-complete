export interface User {
  id: number;
  email: string;
  admin: boolean;
  password: string;
  createdAt: Date;
  updatedAt?: Date;
}
