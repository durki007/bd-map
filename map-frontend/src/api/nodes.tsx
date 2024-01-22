import axios from 'axios';

export interface MapNode {
  id: number;
  posX: number;
  posY: number;
  blockedBy: number;
  timestamp: string;
  nodeType: string;
}

export interface EditedMapNode {
  id: number;
  posX: number;
  posY: number;
  timestamp: string;
  nodeType: string;
}

export interface MapNodeType {
  id: number;
  name: string;
}

// endpoint types
export interface AddMapNode {
  posX: number;
  posY: number;
}

export interface EditMapNode {
  nodeId: number;
  changesetId: number;
  posX: number;
  posY: number;
}

export function isNode(obj: any): obj is MapNode {
  return obj?.nodeType !== undefined;
}

export async function getNodes(): Promise<MapNode[]> {
  const { data } = await axios.get('http://localhost:8080/nodes');
  return data;
}

export async function addNode(nodes: AddMapNode[]): Promise<MapNode[]> {
  return axios.post(`http://localhost:8080/admin/nodes`, nodes);
}

export async function editNode(node: EditMapNode) {
  return axios.put(
    `http://localhost:8080/editor/node?nodeId=${node.nodeId}&changesetId=${node.changesetId}`,
    {
      posX: node.posX,
      posY: node.posY,
    },
  );
}
