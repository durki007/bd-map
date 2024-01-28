import axios from 'axios';
import { Changeset } from './changeset';
import { EditedNode, Node } from './nodes';
import { EditedWay, Way } from './ways';
import { API_URL } from '../constants';

export interface WayNode {
  id: number;
  wayId: number;
  node1Id: number;
  node2Id: number;
  blockedBy: number;
  way?: Way;
  node1?: Node;
  node2?: Node;
}

export interface EditedWayNode {
  id: number;
  timestamp: string;
  wayId: number;
  node1Id: number;
  node2Id: number;
}

// endpoint types
export interface AddWayNode {
  name: string;
  wayType: string;
}

export interface EditWayNode {
  wayNodeId: number;
  changesetId: number;
  wayId: number;
  node1Id: number;
  node2Id: number;
}

export function isWayNode(obj: any): obj is WayNode {
  return obj?.node1Id !== undefined;
}

export function extendMapWayNodeWithWay(
  mapWayNodes: WayNode[],
  mapWays: Way[],
): WayNode[] {
  return mapWayNodes.map((wayNode) => {
    const matchingWay = mapWays.find((way) => way.id === wayNode.wayId);
    return {
      ...wayNode,
      way: matchingWay,
    };
  });
}

export function extendMapWayNodeWithNodes(
  mapWayNodes: WayNode[],
  mapNodes: Node[],
): WayNode[] {
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

export async function getWayNodes() {
  const { data } = await axios.get<WayNode[]>(`${API_URL}/wayNodes`);
  return data;
}

export async function addWayNode(ways: AddWayNode[]) {
  return axios.post<WayNode[]>(`${API_URL}/admin/wayNodes`, ways);
}

export async function editWayNode(wayNode: EditWayNode) {
  return axios.put(
    `${API_URL}/editor/wayNode?wayNodeId=${wayNode.wayNodeId}&changesetId=${wayNode.changesetId}`,
    {
      wayId: wayNode.wayId,
      node1Id: wayNode.node1Id,
      node2Id: wayNode.node2Id,
    },
  );
}

export async function editAll(wayNode: WayNode) {
  const changeset = await axios.post<Changeset>(
    `${API_URL}/editor/changeset?userId=1`,
  );

  const way = await axios.put<EditedWay>(
    `${API_URL}/editor/way?wayId=${wayNode.way?.id}&changesetId=${changeset.data.id}`,
    {
      name: wayNode.way?.name,
      wayType: wayNode.way?.wayType,
    },
  );

  const node1 = await axios.put<EditedNode>(
    `${API_URL}/editor/node?nodeId=${wayNode.node1?.id}&changesetId=${changeset.data.id}`,
    {
      posX: wayNode.node1?.posX,
      posY: wayNode.node1?.posY,
    },
  );

  const node2 = await axios.put<EditedNode>(
    `${API_URL}/editor/node?nodeId=${wayNode.node2?.id}&changesetId=${changeset.data.id}`,
    {
      posX: wayNode.node2?.posX,
      posY: wayNode.node2?.posY,
    },
  );

  return await axios.put<EditedWayNode>(
    `${API_URL}/editor/wayNode?wayNodeId=${wayNode.id}&changesetId=${changeset.data.id}`,
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
