export interface Post {
  id: number;
  title: string;
  content: string;
  author: number;
  authorUsername: string;
  topic: number;
  createdAt: Date;
  updatedAt?: Date;
}
