import axios from 'axios';

export interface Node {
  id: number;
  posX: number;
  posY: number;
  blockedBy: number;
  timestamp: string;
  nodeType: string;
}

export interface EditedNode {
  id: number;
  posX: number;
  posY: number;
  timestamp: string;
  nodeType: string;
}

export interface NodeType {
  id: number;
  name: string;
}

// endpoint types
export interface AddNode {
  posX: number;
  posY: number;
}

export interface EditNode {
  nodeId: number;
  changesetId: number;
  posX: number;
  posY: number;
}

export function isNode(obj: any): obj is Node {
  return obj?.nodeType !== undefined;
}

export async function getNodes(): Promise<Node[]> {
  const { data } = await axios.get('http://localhost:8080/nodes');
  return data;
}

export async function addNode(nodes: AddNode[]): Promise<Node[]> {
  return axios.post(`http://localhost:8080/admin/nodes`, nodes);
}

export async function editNode(node: EditNode) {
  return axios.put(
    `http://localhost:8080/editor/node?nodeId=${node.nodeId}&changesetId=${node.changesetId}`,
    {
      posX: node.posX,
      posY: node.posY,
    },
  );
}
