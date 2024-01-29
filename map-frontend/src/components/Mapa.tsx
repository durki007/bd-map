'use client';

import { useQuery } from '@tanstack/react-query';
import 'leaflet-defaulticon-compatibility';
import 'leaflet-defaulticon-compatibility/dist/leaflet-defaulticon-compatibility.css';
import 'leaflet/dist/leaflet.css';
import { useState } from 'react';
import { MapContainer, Marker, TileLayer } from 'react-leaflet';
import { css } from 'styled-system/css';
import { Container, Flex } from 'styled-system/jsx';
import { Changeset, getChangesets } from '~/api/changeset';
import { MapArea, MapWay, getMapArea } from '~/api/map-area';
import { Node } from '~/api/nodes';
import ClickableMarker from './ClickableMarker';
import ClickablePolyline from './ClickablePolyline';
import { Sidebar } from './Sidebar';
import { SidebarChangeset } from './SidebarChangeset';

const Mapa = (props: { mapArea: MapArea; changesets: Changeset[] }) => {
  const { data: mapArea } = useQuery({
    queryKey: ['mapArea'],
    queryFn: async () =>
      getMapArea({ maxX: 1000.0, minX: 0.0, maxY: 340.0, minY: 0.0 }),
    initialData: props.mapArea,
  });

  const { data: changesets, isSuccess: isChangesetsSuccess } = useQuery({
    queryKey: ['changesets'],
    queryFn: async () => getChangesets({ userId: 2 }),
    initialData: props.changesets,
  });

  const [activeChangeset, setActiveChangeset] = useState<boolean>(false);
  const [currentChangeset, setCurrentChangeset] = useState<Changeset>();

  if (
    isChangesetsSuccess &&
    activeChangeset &&
    currentChangeset === undefined
  ) {
    console.log(currentChangeset);
    console.log(changesets);
    setCurrentChangeset(changesets.at(-1));
    console.log(currentChangeset);
  }

  const [selectedObject, setSelectedObject] = useState<Node | MapWay>();

  const handleChangesetClick = (active: boolean) => {
    setActiveChangeset(active);

    if (!active) {
      setCurrentChangeset(undefined);
    }
  };

  const handleObjectClick = (node: any) => {
    setSelectedObject(node);
  };

  console.log('--------------');
  console.log('changeset curr', currentChangeset);
  console.log('active ch', activeChangeset);

  return (
    <Flex gap="10">
      <MapContainer
        className={css({ height: '100vh', width: '100vh' })}
        center={[51.10866, 17.05947]}
        zoom={16}
      >
        <TileLayer
          attribution='&amp;copy <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors'
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
        <Marker position={[51.10866, 17.05947]}></Marker>
        {mapArea.singularNodes?.map((node) => (
          <ClickableMarker
            key={node.id}
            node={node}
            handleClick={handleObjectClick}
          />
        ))}
        {mapArea.ways.map((way) => (
          <ClickablePolyline
            key={way.id}
            way={way}
            handleClick={handleObjectClick}
          />
        ))}
      </MapContainer>
      <Container
        className={css({
          width: '20vw',
          bg: 'bg.subtle',
          overflow: 'scroll',
          maxH: '100vh',
        })}
      >
        {activeChangeset === false ? (
          <SidebarChangeset handleClick={handleChangesetClick} />
        ) : (
          <Sidebar
            object={selectedObject}
            changeset={currentChangeset!}
            handleChangesetClick={handleChangesetClick}
          />
        )}
      </Container>
    </Flex>
  );
};

export default Mapa;
