import axios from 'axios';

export interface MapWay {
  id: number;
  name: string;
  blockedBy: number;
  timestamp: string;
  wayType: string;
}

export interface EditedMapWay {
  id: number;
  name: string;
  timestamp: string;
  wayType: string;
}

// endpoint types
export interface AddMapWay {
  name: string;
  wayType: string;
}

export interface EditMapWay {
  wayId: number;
  changesetId: number;
  name: string;
  wayType: string;
}

export function isWay(obj: any): obj is MapWay {
  return obj?.wayType !== undefined;
}

export async function getWays(): Promise<MapWay[]> {
  const { data } = await axios.get('http://localhost:8080/ways');
  return data;
}

export async function addWay(ways: AddMapWay[]): Promise<MapWay[]> {
  return axios.post(`http://localhost:8080/admin/ways`, ways);
}

export async function editWay(way: EditMapWay) {
  return axios.put(
    `http://localhost:8080/editor/way?wayId=${way.wayId}&changesetId=${way.changesetId}`,
    {
      name: way.name,
      wayType: way.wayType,
    },
  );
}
