import axios from 'axios';

export interface User {
  id: number;
  username: string;
  email: string;
  role: string;
}

export interface PrivateUser extends User {
  password: string;
  changeset: number[];
  ormid: number;
}

// endpoint types
export interface AddUser {
  username: string;
  email: string;
  password: string;
  role: string;
}

export interface DeleteUser {
  id: number;
}

export async function getUsers(): Promise<User[]> {
  const { data } = await axios.get('http://localhost:8080/users');
  return data;
}

export async function addUser(user: AddUser): Promise<PrivateUser> {
  return axios.post(`http://localhost:8080/users`, user);
}

export async function deleteUser(user: DeleteUser) {
  return axios.delete(`http://localhost:8080/users/${user.id}`);
}