'use client';

import { Polyline } from 'react-leaflet';
import { MapWay } from '~/api/map-area';

const ClickablePolyline = (props: { way: MapWay; handleClick: any }) => {
  const way = props.way;

  const color =
    way.wayType === 'autostrada'
      ? 'red'
      : way.wayType === 'droga ekspresowa'
        ? 'blue'
        : way.wayType === 'droga krajowa'
          ? 'green'
          : way.wayType === 'droga wojewódzka'
            ? 'yellow'
            : way.wayType === 'droga powiatowa'
              ? 'orange'
              : way.wayType === 'droga gminna'
                ? 'purple'
                : 'black';

  return (
    <>
      {way.wayNodes.map((wayNode) => (
        <Polyline
          eventHandlers={{
            click: () => {
              props.handleClick(way);
            },
          }}
          key={wayNode.id}
          positions={[
            [wayNode.node1.posX, wayNode.node1.posY],
            [wayNode.node2.posX, wayNode.node2.posY],
          ]}
          color={color}
          fill={true}
        />
      ))}
    </>
  );
};

export default ClickablePolyline;
