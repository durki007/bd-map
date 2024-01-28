'use client';

import { Marker, Popup } from 'react-leaflet';
import { Node } from '~/api/nodes';

const ClickableMarker = (props: { node: Node; handleClick: any }) => {
  const node = props.node;

  return (
    <Marker
      eventHandlers={{
        click: () => {
          props.handleClick(node);
        },
      }}
      position={[node.posX, node.posY]}
    >
      <Popup>
        id: {node.id}
        <br />
        posX: {node.posX}
        <br />
        posY: {node.posY}
        <br />
        blockedBy: {node.blockedBy}
        <br />
        timestamp: {node.timestamp}
        <br />
        nodeType: {node.nodeType}
      </Popup>
    </Marker>
  );
};

export default ClickableMarker;
