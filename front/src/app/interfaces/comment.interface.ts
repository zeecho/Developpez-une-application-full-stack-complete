export interface Comment {
  id: number;
  content: string;
  author: number;
  authorUsername: string;
  article: number;
  createdAt: Date;
  updatedAt?: Date;
}