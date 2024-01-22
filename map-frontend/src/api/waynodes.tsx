import axios from 'axios';
import { EditedMapWay, MapWay } from './ways';
import { EditedMapNode, MapNode } from './nodes';

export interface MapWayNode {
  id: number;
  wayId: number;
  node1Id: number;
  node2Id: number;
  blockedBy: number;
  way?: MapWay;
  node1?: MapNode;
  node2?: MapNode;
}

export interface EditedMapWayNode {
  id: number;
  timestamp: string;
  wayId: number;
  node1Id: number;
  node2Id: number;
}

// endpoint types
export interface AddMapWayNode {
  name: string;
  wayType: string;
}

export interface EditMapWayNode {
  wayNodeId: number;
  changesetId: number;
  wayId: number;
  node1Id: number;
  node2Id: number;
}

export function isWayNode(obj: any): obj is MapWayNode {
  return obj?.node1Id !== undefined;
}

export function extendMapWayNodeWithWay(
  mapWayNodes: MapWayNode[],
  mapWays: MapWay[],
): MapWayNode[] {
  return mapWayNodes.map((wayNode) => {
    const matchingWay = mapWays.find((way) => way.id === wayNode.wayId);
    return {
      ...wayNode,
      way: matchingWay,
    };
  });
}

export function extendMapWayNodeWithNodes(
  mapWayNodes: MapWayNode[],
  mapNodes: MapNode[],
): MapWayNode[] {
  return mapWayNodes.map((wayNode) => {
    const matchingNode1 = mapNodes.find((node) => node.id === wayNode.node1Id);
    const matchingNode2 = mapNodes.find((node) => node.id === wayNode.node2Id);

    return {
      ...wayNode,
      node1: matchingNode1,
      node2: matchingNode2,
    };
  });
}

export async function getWayNodes(): Promise<MapWayNode[]> {
  const { data } = await axios.get('http://localhost:8080/wayNodes');
  return data;
}

export async function addWayNode(ways: AddMapWayNode[]): Promise<MapWayNode[]> {
  return axios.post(`http://localhost:8080/admin/wayNodes`, ways);
}

export async function editWayNode(wayNode: EditMapWayNode) {
  return axios.put(
    `http://localhost:8080/editor/wayNode?wayNodeId=${wayNode.wayNodeId}&changesetId=${wayNode.changesetId}`,
    {
      wayId: wayNode.wayId,
      node1Id: wayNode.node1Id,
      node2Id: wayNode.node2Id,
    },
  );
}

export interface Changeset {
  id: number;
  creationDate: string;
  closeDate: string | null;
  userInfo: {
    id: number;
    username: string;
    email: string;
    role: string;
  };
}

export async function editAll(wayNode: MapWayNode) {
  const changeset = await axios.post<Changeset>(
    `http://localhost:8080/editor/changeset?userId=1`,
  );

  const way = await axios.put<EditedMapWay>(
    `http://localhost:8080/editor/way?wayId=${wayNode.way?.id}&changesetId=${changeset.data.id}`,
    {
      name: wayNode.way?.name,
      wayType: wayNode.way?.wayType,
    },
  );

  const node1 = await axios.put<EditedMapNode>(
    `http://localhost:8080/editor/node?nodeId=${wayNode.node1?.id}&changesetId=${changeset.data.id}`,
    {
      posX: wayNode.node1?.posX,
      posY: wayNode.node1?.posY,
    },
  );

  const node2 = await axios.put<EditedMapNode>(
    `http://localhost:8080/editor/node?nodeId=${wayNode.node2?.id}&changesetId=${changeset.data.id}`,
    {
      posX: wayNode.node2?.posX,
      posY: wayNode.node2?.posY,
    },
  );

  return await axios.put<EditedMapWayNode>(
    `http://localhost:8080/editor/wayNode?wayNodeId=${wayNode.id}&changesetId=${changeset.data.id}`,
    {
      wayId: wayNode.wayId,
      node1Id: wayNode.node1Id,
      node2Id: wayNode.node2Id,
    },
  );

  // return new Promise((resolve) => {
  //   wayNode.way = { blockedBy: wayNode.way?.blockedBy!, ...way.data };
  //   wayNode.node1 = { blockedBy: wayNode.node1?.blockedBy!, ...node1.data };
  //   wayNode.node2 = { blockedBy: wayNode.node2?.blockedBy!, ...node2.data };

  //   resolve(way);
  // });
}
