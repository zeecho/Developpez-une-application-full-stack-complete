export interface Comment {
  id: number;
  content: string;
  author: number;
  authorUsername: string;
  post: number;
  createdAt: Date;
  updatedAt?: Date;
}