import axios from 'axios';
import { User } from './users';
import { API_URL } from '../constants';

export interface Changeset {
  id: number;
  creationDate: string;
  closeDate: string | null;
  userInfo: User;
}

export interface ClosedChangeset extends Changeset {
  closeDate: string;
}

// endpoint types
export interface GetChangeset {
  userId: number;
}

export interface CreateChangeset {
  userId: number;
}

export interface CloseChangeset {
  id: number;
}

export async function getChangesets(props: GetChangeset) {
  const { data } = await axios.get<Changeset[]>(
    `${API_URL}/editor/changeset?userId=${props.userId}`,
  );
  return data;
}

export async function createChangeset(props: CreateChangeset) {
  return axios.post<Changeset>(
    `${API_URL}/editor/changeset?userId=${props.userId}`,
  );
}

export async function closeChangeset(props: CloseChangeset) {
  console.log('in close', props)
  return axios.delete<ClosedChangeset>(
    `${API_URL}/editor/changeset/${props.id}`,
  );
}
