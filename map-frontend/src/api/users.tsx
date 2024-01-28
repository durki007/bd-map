import axios from 'axios';
import { API_URL } from '../constants';

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

export async function getUsers() {
  const { data } = await axios.get<User[]>(`${API_URL}/users`);
  return data;
}

export async function addUser(user: AddUser) {
  return axios.post<PrivateUser>(`${API_URL}/users`, user);
}

export async function deleteUser(user: DeleteUser) {
  return axios.delete(`${API_URL}/users/${user.id}`);
}
