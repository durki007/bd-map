import axios from 'axios';
import { API_URL } from '../constants';

export interface Way {
  id: number;
  name: string;
  blockedBy: number;
  wayType: string;
  timestamp: string;
}

export interface EditedWay {
  id: number;
  name: string;
  timestamp: string;
  wayType: string;
}

// endpoint types
export interface AddWay {
  name: string;
  wayType: string;
}

export interface EditWay {
  wayId: number;
  changesetId: number;
  name: string;
  wayType: string;
}

export function isWay(obj: any): obj is Way {
  return obj?.wayType !== undefined;
}

export async function getWays() {
  const { data } = await axios.get<Way[]>(`${API_URL}/ways`);
  return data;
}

export async function addWay(ways: AddWay[]) {
  return axios.post<Way[]>(`${API_URL}/admin/ways`, ways);
}

export async function editWay(way: EditWay) {
  return axios.put(
    `${API_URL}/editor/way?wayId=${way.wayId}&changesetId=${way.changesetId}`,
    {
      name: way.name,
      wayType: way.wayType,
    },
  );
}
