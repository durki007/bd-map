'use client';

import { useQuery } from '@tanstack/react-query';
import 'leaflet-defaulticon-compatibility';
import 'leaflet-defaulticon-compatibility/dist/leaflet-defaulticon-compatibility.css';
import 'leaflet/dist/leaflet.css';
import { useState } from 'react';
import { MapContainer, Marker, TileLayer } from 'react-leaflet';
import { css } from 'styled-system/css';
import { Container, Flex } from 'styled-system/jsx';
import { MapNode, getNodes } from '~/api/nodes';
import {
  MapWayNode,
  extendMapWayNodeWithNodes,
  extendMapWayNodeWithWay,
  getWayNodes,
} from '~/api/waynodes';
import { MapWay, getWays } from '~/api/ways';
import ClickableMarker from './ClickableMarker';
import ClickablePolyline from './ClickablePolyline';
import { Sidebar } from './Sidebar';

const Mapa = (props: {
  nodes: MapNode[];
  ways: MapWay[];
  wayNodes: MapWayNode[];
}) => {
  const { data: nodes } = useQuery({
    queryKey: ['nodes'],
    queryFn: getNodes,
    initialData: props.nodes,
  });
  const { data: ways } = useQuery({
    queryKey: ['ways'],
    queryFn: getWays,
    initialData: props.ways,
  });
  const { data: wayNodes } = useQuery({
    queryKey: ['wayNodes'],
    queryFn: getWayNodes,
    initialData: props.wayNodes,
  });

  // XD
  const [extendedWayNodes, setExtendedWayNodes] = useState<MapWayNode[]>(
    extendMapWayNodeWithNodes(extendMapWayNodeWithWay(wayNodes, ways), nodes),
  );

  const [selectedObject, setSelectedObject] = useState<
    MapNode | MapWay | MapWayNode
  >();

  const handleObjectClick = (node: MapNode) => {
    setSelectedObject(node);
  };

  return (
    <Flex gap="10">
      <MapContainer
        className={css({ height: '100vh', width: '100vh' })}
        center={[1, 4]}
        zoom={16}
      >
        <TileLayer
          attribution='&amp;copy <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors'
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
        <Marker position={[51.10866, 17.05947]}></Marker>
        {nodes?.map((node) => (
          <ClickableMarker
            key={node.id}
            node={node}
            handleClick={handleObjectClick}
          />
        ))}
        {extendedWayNodes?.map((wayNode) => (
          <ClickablePolyline
            key={wayNode.id}
            wayNode={wayNode}
            handleClick={handleObjectClick}
          />
        ))}
      </MapContainer>
      <Container
        className={css({
          width: '20vw',
          bg: 'bg.subtle',
          overflow: 'scroll',
        })}
      >
        <Sidebar object={selectedObject} />
      </Container>
    </Flex>
  );
};

export default Mapa;
