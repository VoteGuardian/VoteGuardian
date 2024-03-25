'use client'
import InfoContent from "@/components/VoteCreate/VoteInformation/InfoContent";
import VoteMenu from "@/components/VoteCreate/VoteMenu/VoteMenu";
import { RecoilRoot } from "recoil";
import './Information.scss'

export default function Information() {
    return (
        <RecoilRoot>
            <div className="vote-info-flex">
                <VoteMenu/>
                <InfoContent/>
            </div>
        </RecoilRoot>
    )
}