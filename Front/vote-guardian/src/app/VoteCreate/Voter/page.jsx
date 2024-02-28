'use client'
import { RecoilRoot } from "recoil";
import VoteMenu from "@/components/VoteMenu/VoteMenu";
import VoterContent from "@/components/VoterContent/VoterContent";
import './Voter.scss'


export default function Voter() {
    return (
        <RecoilRoot>
            <div className="voter-flex">
                <VoteMenu/>
                <VoterContent/>
            </div>
        </RecoilRoot>
    )
}