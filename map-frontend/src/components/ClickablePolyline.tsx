'use client';

import { Polyline } from 'react-leaflet';
import { MapWayNode } from '~/api/waynodes';

const ClickablePolyline = (props: {
  wayNode: MapWayNode;
  handleClick: any;
}) => {
  const wayNode = props.wayNode;

  return (
    <Polyline
      eventHandlers={{
        click: () => {
          props.handleClick(wayNode);
        },
      }}
      positions={[
        [wayNode.node1?.posX!, wayNode.node1?.posY!],
        [wayNode.node2?.posX!, wayNode.node2?.posY!],
      ]}
    />
  );
};

export default ClickablePolyline;
