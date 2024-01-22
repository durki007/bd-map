'use client';

import { useMutation, useQueryClient } from '@tanstack/react-query';
import { useEffect, useState } from 'react';
import { css } from 'styled-system/css';
import { Flex, HStack, Stack } from 'styled-system/jsx';
import { MapNode, editNode, isNode } from '~/api/nodes';
import { MapWayNode, editWayNode, isWayNode } from '~/api/waynodes';
import { MapWay, editWay, isWay } from '~/api/ways';
import { Button } from './ui/button';
import { FormLabel } from './ui/form-label';
import { Heading } from './ui/heading';
import { Input } from './ui/input';
import { NumberInput } from './ui/number-input';
import { Text } from './ui/text';

export const Sidebar = (props: { object?: MapNode | MapWay | MapWayNode }) => {
  const object = props.object;

  const [currentObject, setCurrentObject] = useState(object);
  const queryClient = useQueryClient();

  useEffect(() => {
    setCurrentObject(object);
  }, [object]);

  const editWayMutation = useMutation({
    mutationFn: editWay,
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ['ways'],
      });
    },
  });

  const editNodeMutation = useMutation({
    mutationFn: editNode,
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ['nodes'],
      });
    },
  });

  const editWayNodeMutation = useMutation({
    mutationFn: editWayNode,
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ['wayNodes'],
      });
    },
  });

  const renderNode = (currentObject: MapNode) => {
    return (
      <Stack bg="bg.muted" padding="4">
        <Heading textStyle={'xl'}>Node</Heading>
        <Text as="p">id: {currentObject.id}</Text>
        <NumberInput defaultValue={currentObject.posX.toString()}>
          posX
        </NumberInput>
        <NumberInput defaultValue={currentObject.posY.toString()}>
          posY
        </NumberInput>
        <Text as="p">
          blockedBy:{' '}
          {currentObject.blockedBy === 0 ? 'none' : currentObject.blockedBy}
        </Text>
        <Text as="p">
          timestamp: {new Date(currentObject.timestamp).toLocaleString('pl-PL')}
        </Text>
        <Stack gap="1.5" width="2xs">
          <FormLabel htmlFor="nodeType">nodeType</FormLabel>
          <Input id="nodeType" defaultValue={currentObject.nodeType} />
        </Stack>
      </Stack>
    );
  };

  const renderWay = (currentObject: MapWay) => {
    return (
      <Stack bg="bg.muted" padding="4">
        <Heading textStyle={'xl'}>Way</Heading>
        <Text as="p">id: {currentObject.id}</Text>
        <Stack gap="1.5" width="2xs">
          <FormLabel htmlFor="name">name</FormLabel>
          <Input id="name" defaultValue={currentObject.name} />
        </Stack>
        <Text as="p">
          blockedBy:{' '}
          {currentObject.blockedBy === 0 ? 'none' : currentObject.blockedBy}
        </Text>
        <Text as="p">
          timestamp: {new Date(currentObject.timestamp).toLocaleString('pl-PL')}
        </Text>
        <Stack gap="1.5" width="2xs">
          <FormLabel htmlFor="wayType">wayType</FormLabel>
          <Input id="wayType" defaultValue={currentObject.wayType} />
        </Stack>
      </Stack>
    );
  };

  const renderWayNode = (currentObject: MapWayNode) => {
    return (
      <Stack bg="bg.muted" padding="4">
        <Heading textStyle={'xl'}>WayNode</Heading>
        <Text as="p">id: {currentObject.id}</Text>
        <NumberInput
          defaultValue={currentObject.wayId.toString()}
          min={0}
          formatOptions={{ maximumFractionDigits: 0 }}
        >
          wayId
        </NumberInput>
        <NumberInput
          defaultValue={currentObject.node1Id.toString()}
          min={0}
          formatOptions={{ maximumFractionDigits: 0 }}
        >
          node1Id
        </NumberInput>
        <NumberInput
          defaultValue={currentObject.node2Id.toString()}
          min={0}
          formatOptions={{ maximumFractionDigits: 0 }}
        >
          node2Id
        </NumberInput>
        <Text as="p">
          blockedBy:{' '}
          {currentObject.blockedBy === 0 ? 'none' : currentObject.blockedBy}
        </Text>
      </Stack>
    );
  };

  const renderFields = () => {
    if (isNode(currentObject)) {
      return renderNode(currentObject);
    } else if (isWay(currentObject)) {
      return renderWay(currentObject);
    } else if (isWayNode(currentObject)) {
      return (
        <>
          {renderWayNode(currentObject)}
          {currentObject.way !== undefined
            ? renderWay(currentObject.way!)
            : null}
          {currentObject.node1 !== undefined
            ? renderNode(currentObject.node1!)
            : null}
          {currentObject.node2 !== undefined
            ? renderNode(currentObject.node2!)
            : null}
        </>
      );
    }

    return null;
  };

  const handleSave = () => {
    const way = currentObject?.way as MapWay;
    const node1 = currentObject?.node1 as MapNode;
    const node2 = currentObject?.node2 as MapNode;

    editWayMutation.mutate({
      wayId: way.id,
      changesetId: 102,
      name: way.name!,
      wayType: way.wayType!,
    });
    editNodeMutation.mutate({
      nodeId: node1.id,
      changesetId: 102,
      posX: node1.posX,
      posY: node1.posY,
    });
    editNodeMutation.mutate({
      nodeId: node2.id,
      changesetId: 102,
      posX: node2.posX,
      posY: node2.posY,
    });
    editWayNodeMutation.mutate({
      wayNodeId: currentObject?.id as number,
      changesetId: 102,
      wayId: currentObject?.wayId as number,
      node1Id: currentObject?.node1Id as number,
      node2Id: currentObject?.node2Id as number,
    });
  };

  return (
    <Flex
      className={css({
        justifyContent: 'center',
        alignItems: 'center',
        flexDir: 'column',
      })}
    >
      <HStack>
        <Heading textStyle={'2xl'} marginY="2">
          Edytor
        </Heading>
        <Button onClick={handleSave}>Zapisz</Button>
      </HStack>
      <Stack maxH="90vh" gap="14">
        {renderFields()}
      </Stack>
    </Flex>
  );
};
