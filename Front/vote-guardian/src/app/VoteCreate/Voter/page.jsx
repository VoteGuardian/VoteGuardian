'use client'
import { RecoilRoot } from "recoil";
import VoteMenu from "@/components/VoteCreate/VoteMenu/VoteMenu";
import VoterContent from "@/components/VoteCreate/Voter/Content/VoterContent";
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