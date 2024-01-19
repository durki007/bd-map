'use client';

import 'leaflet-defaulticon-compatibility';
import 'leaflet-defaulticon-compatibility/dist/leaflet-defaulticon-compatibility.css';
import 'leaflet/dist/leaflet.css';
import { MapContainer, Marker, TileLayer } from 'react-leaflet';
import { css } from 'styled-system/css';

const Mapa = () => {
  return (
    <div>
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
      </MapContainer>
    </div>
  );
};

export default Mapa;
