import axios from 'axios';
import type { Node } from './nodes';
import type { Way } from './ways';
import { API_URL } from '../constants';

export interface MapWayNode {
  id: number;
  node1: Node;
  node2: Node;
}

export interface MapWay extends Way {
  wayNodes: MapWayNode[];
}

export interface MapArea {
  maxX: number;
  minX: number;
  maxY: number;
  minY: number;
  singularNodes: Node[];
  ways: MapWay[];
}

// endpoint types
export interface GetMapArea {
  maxX: number;
  minX: number;
  maxY: number;
  minY: number;
}

export function isMapWay(obj: any): obj is MapWay {
  return obj?.wayNodes !== undefined;
}

export async function getMapArea(area: GetMapArea) {
  const { data } = await axios.get<MapArea>(
    `${API_URL}/map/screen?maxX=${area.maxX}&minX=${area.minX}&maxY=${area.maxY}&minY=${area.minY}`,
  );
  return data;
}
