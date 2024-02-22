'use client'
import InfoContent from "@/components/VoteInformation/InfoContent";
import InfoMenu from "@/components/VoteInformation/InfoMenu";
import { RecoilRoot } from "recoil";
import './Information.scss'

export default function Information() {
    return (
        <RecoilRoot>
            <div className="vote-info-flex">
                <InfoMenu/>
                <InfoContent/>
            </div>
        </RecoilRoot>
    )
}