'use client'
import { RecoilRoot } from 'recoil';
import VoteSelect from '@/components/VoteSelect/VoteSelect';

export default function Select() {
    return (
        <RecoilRoot>
            <VoteSelect></VoteSelect>
        </RecoilRoot>
    )
}